package cn.mybatis;

/**
 * @Bug Scene:
 * 拷贝了一份在mysql，数据库框架使用mybatisplus到新项目，就数据库不一样是postgresql，驱动不一样
 * 老环境: mysql8.0.21: mysql-connector-java:8.0.29 Specification-Version: 4.2 但是能支持1.8
 * 新环境: postgresql9.5.5: 9.4-1206-jdbc42 Specification-Version: 4.2 但是实际上只能支持到1.7
 * 推荐42.5.4 42以上的版本都能支持1.8
 *
 * 参考文档:
 * https://blog.csdn.net/WTUDAN/article/details/113248470
 *
 * 例子参考: TestModel类
 *
 */
public class Java8SupportForPostgresql {

    public static void main(String[] args) {

    }
}
