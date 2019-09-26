# generalpack
以下说明只限定于Sprintboat
##对数据库的操作
1.引入对mysql的依赖 mysql-connection-java  
2.映入JPA的依赖 spring-boot-starter-data-jpa  
3.配置数据库信息  
  a.) application.yml  
    1.)配置驱动程序  
        驱动程序必须用com.mysql.cj.jdbc.Driver
        不可以使用com.mysql.jdbc.Driver，后者已被废弃
        可能需要加入serverTimezone=UTC，防止服务器与客户端时钟不统一
    2.)配置用户名  
    3.)配置密码  
    4.)数据库路径  
    5.)如果调试时想要打印出sql语句, 设置show-sql=true  
4.建立数据库对象实体类（个人爱好，我喜欢用Domain）  
    切记，springjpa的表名和实体类的对应方法，如果想要驼峰，必须表名的前面是_
    如果实在是记不住，或者觉得这个方式不好，使用@Table(name="表名“)，手工指定  
    实体类要加入@Entity @Data注解
    @Entity 表明该类为一个实体类，它默认对应数据库中的表名是类名
    @Data 属于Lombok 我的理解就是可以少写set/get方法，   
    切记 切记 切记 自增字段如果数据库选了自动增长，必须使用@GeneratedValue(strategy= GenerationType.IDENTITY)  
5.建立数据访问对象类（个人爱好，我喜欢用DAO)
6.如果在修改的时候，只想修改想要修改的字段，使用@DynamicUpdate注解
##service层  
1. service层的对象必须加入一个@service注解  
2. 使用@Autowire织入DAO层对象
##controller层  
1. 返回JSON格式数据，使用@RestController注解  
ps.url前缀由application.yml里server.servlet.context定义
##定义VO对象(ViewObject)  
1. 用于返回给前端的对象
2. 如果特殊原因后端类名无法使返回前端的名称，使用@JsonProperty注解,进行转义  
##单元测试
1. 必须在类前面加入注解
    @RunWith(SpringRunner.class)
    @SpringBootTest
2. 注入需要的Service或者DAO，诸如此类    
    @Autowired
3. 为了保证测试数据不污染数据库，使用@Transactional，让事务回滚  
##XML
1. 产生XML文件  
    a.)先创立Document对象 
        Document document = new DocmentHelp.createDocument()    
    b.)创建根节点  
        Element root = document.addElement($text)
    c.)创建子元素  
        Element sub = root.addElement($text)
    e.)元素名称
        sub.addText($text)
    d.)元素添加属性  
        sub.addAttribute($attrib, $value)
    e.)流输出  
        OutputFormat outputFormat = OutputFormat.createPrettyPrint()
        XMLWriter xmlWriter = new XMLWriter(new FileWriter(new File($filePath)), outputFormat)
        xmlWriter.write(doc)
        xmlWriter.close();