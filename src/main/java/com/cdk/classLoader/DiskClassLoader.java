package com.cdk.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DiskClassLoader extends ClassLoader {
    //super();隐式语句
    private String mLibPath;

    public DiskClassLoader(String path) {
        // TODO Auto-generated constructor stub
        mLibPath = path;
    }

    /**
     * Finds the class with the specified <a href="#name">binary name</a>.
     * This method should be overridden by class loader implementations that
     * follow the delegation model for loading classes, and will be invoked by
     * the {@link #loadClass <tt>loadClass</tt>} method after checking the
     * parent class loader for the requested class.  The default implementation
     * throws a <tt>ClassNotFoundException</tt>.
     *
     * @param  name
     *         The <a href="#name">binary name</a> of the class
     *
     * @return The resulting <tt>Class</tt> object
     *
     * @throws ClassNotFoundException
     *          If the class could not be found
     *
     * @since 1.2
     */
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = getFileName(name);
        //根据 parent 路径名字符串和 child 路径名字符串创建一个新 File 实例。
        File file = new File(mLibPath, fileName);

        try {
            FileInputStream is = new FileInputStream(file);
            //class文件是字节码格式文件
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len = 0;
            try {
                while ((len = is.read()) != -1) {
                    bos.write(len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] data = bos.toByteArray();
            is.close();
            bos.close();
            //defineClass()生成了Class对象
            return defineClass(name, data, 0, data.length);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    //获取要加载 的class文件名
    private String getFileName(String name) {
        // TODO Auto-generated method stub
        int index = name.lastIndexOf('.');
        if (index == -1) {
            return name + ".class";
        } else {
            //substring(x)是从字符串的的第x个字符截取
            return name.substring(index + 1) + ".class";
        }
    }
}
