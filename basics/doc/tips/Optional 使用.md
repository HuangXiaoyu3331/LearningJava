# Optional 解决 NPE
当一个`bean`有多级嵌套的时候，当要获取对象中的某个属性时，为了避免空指针问题，往往会使用多个`if`、`else`来进行判空，如：
```java
private String getProvince(Persion persion){
    if(perison != null) {
        if(persion.getAddress() != null) {
            return persion.getProvince();
        }
    }
    return "none";
}
```
使用 java8 的`Optional`类可以很好的解决该问题
```java
private String getProvince(Persion persion){
    Optional.ofNullable(persion)
        .map(Persion::getAddress)
        .map(Address::getProvince)
        .orElse("none");
}
```
Optional 在 list 中需要注意一点是，当要获取一个空 list 的元素的时候，使用 map 方法可能报数组越界异常
```java
List<User> userList = new ArrayList();
String username = Optional.ofNullable(userList)
  .map(list -> list.get(0))
  .map(User::getUsername)
  .orElse("");
System.out.println(username);
```
执行上面代码时，会报`IndexOutOfBoundsException`
```java
Exception in thread "main" java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
	at java.util.ArrayList.get(ArrayList.java:435)
	at com.hxy.learning.java.basics.Main.lambda$main$0(Main.java:16)
	at java.util.Optional.map(Optional.java:215)
	at com.hxy.learning.java.basics.Main.main(Main.java:16)
```
对于这种 case，可以在list.get(0)前面加一个 filter 判断 list 是否为空，如
```java
List<User> userList = new ArrayList();
String username = Optional.ofNullable(userList)
	.filter(list -> !list.isEmpty())
  .map(list -> list.get(0))
  .map(User::getUsername)
  .orElse("");
System.out.println(username);
```
当然，即使判断了 list 是否为空，也无法完全避免数组越界异常，比如
```java
List<User> userList = new ArrayList();
userList.add(new User("hxy"));
String username = Optional.ofNullable(userList)
	.filter(list -> !list.isEmpty())
  .map(list -> list.get(3))
  .map(User::getUsername)
  .orElse("");
System.out.println(username);
```
其实也正常，因为 Optional 设计的初衷就是为了避免 NPE，但是没办法避免`IndexOutOfBoundException`
> 参考：https://stackoverflow.com/questions/61062249/how-to-map-value-at-index-0-for-a-list-in-an-optional-stream