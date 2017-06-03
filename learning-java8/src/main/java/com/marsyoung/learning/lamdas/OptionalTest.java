package com.marsyoung.learning.lamdas;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author zhiyuma 在Java中，null
 *         代表一个不存在的对象，如果对它进行操作就会抛出java.lang.NullPointerException异常。
 *         NPE（NullPointerException）是调试程序最常见的异常。
 *         google一下有很多关于方法到底应该返回null还是new一个空对象的讨论。
 *         类似于scala的scala.Option[T]、Haskell的Data.Maybe,Java 8
 *         引入了java.util.Optional
 *         <T>.Opitonal就是一个元素的容器，Java8新添加的很多类或者方法的返回值都使用了Opitonal.
 *         Java5的话可以使用Google
 *         Guava的Optional来实现，Java8的Optional的接口跟Guava的Optional很相似。
 */
public class OptionalTest {

	public void test() {
		String mayBeNull = null;
		Optional<String> opt1 = Optional.of("hello ,mars");
		Optional<String> opt2 = Optional.of(mayBeNull);// 为非null的值创建一个Optional。of方法通过工厂方法创建Optional类。需要注意的是，创建对象时传入的参数不能为null。如果传入参数为null，则抛出NullPointerException
														// 。
		Optional<String> opt3 = Optional.empty();
		opt1.ifPresent(System.out::println);
		opt2.ifPresent(System.out::println);
		opt3.ifPresent(System.out::println);
	}

	public void test2() {
		String mayBeNull = null;
		Optional<String> opt1 = Optional.of("hello ,mars");
		Optional<String> opt2 = Optional.ofNullable(mayBeNull);
		Optional<String> opt3 = Optional.empty();
		opt1.ifPresent(System.out::println);
		opt2.ifPresent(System.out::println);
		opt3.ifPresent(System.out::println);
	}

	public static void main(String[] args) {
		new OptionalTest().test2();
		// 创建Optional实例，也可以通过方法返回值得到。
		Optional<String> name = Optional.of("Sanaulla");

		// 创建没有值的Optional实例，例如值为'null'
		Optional<String> empty = Optional.ofNullable(null);

		// isPresent方法用来检查Optional实例是否有值。
		if (name.isPresent()) {
			// 调用get()返回Optional值。
			System.out.println(name.get());
		}

		try {
			// 在Optional实例上调用get()抛出NoSuchElementException。
			System.out.println(empty.get());
		} catch (NoSuchElementException ex) {
			System.out.println(ex.getMessage());
		}

		// ifPresent方法接受lambda表达式参数。
		// 如果Optional值不为空，lambda表达式会处理并在其上执行操作。
		name.ifPresent((value) -> {
			System.out.println("The length of the value is: " + value.length());
		});

		// 如果有值orElse方法会返回Optional实例，否则返回传入的错误信息。
		System.out.println(empty.orElse("There is no value present!"));
		System.out.println(name.orElse("There is some value!"));

		// orElseGet与orElse类似，区别在于传入的默认值。
		// orElseGet接受lambda表达式生成默认值。
		System.out.println(empty.orElseGet(() -> "Default Value"));
		System.out.println(name.orElseGet(() -> "Default Value"));

		try {
			// orElseThrow与orElse方法类似，区别在于返回值。
			// orElseThrow抛出由传入的lambda表达式/方法生成异常。
			empty.orElseThrow(ValueAbsentException::new);
		} catch (Throwable ex) {
			System.out.println(ex.getMessage());
		}

		// map方法通过传入的lambda表达式修改Optonal实例默认值。
		// lambda表达式返回值会包装为Optional实例。
		Optional<String> upperName = name.map((value) -> value.toUpperCase());
		System.out.println(upperName.orElse("No value found"));

		// flatMap与map（Funtion）非常相似，区别在于lambda表达式的返回值。
		// map方法的lambda表达式返回值可以是任何类型，但是返回值会包装成Optional实例。
		// 但是flatMap方法的lambda返回值总是Optional类型。
		upperName = name.flatMap((value) -> Optional.of(value.toUpperCase()));
		System.out.println(upperName.orElse("No value found"));

		// filter方法检查Optiona值是否满足给定条件。
		// 如果满足返回Optional实例值，否则返回空Optional。
		Optional<String> longName = name.filter((value) -> value.length() > 6);
		System.out.println(longName.orElse("The name is less than 6 characters"));

		// 另一个示例，Optional值不满足给定条件。
		Optional<String> anotherName = Optional.of("Sana");
		Optional<String> shortName = anotherName.filter((value) -> value.length() > 6);
		System.out.println(shortName.orElse("The name is less than 6 characters"));

	}
}

class ValueAbsentException extends Throwable {

	private static final long serialVersionUID = 1L;

	public ValueAbsentException() {
		super();
	}

	public ValueAbsentException(String msg) {
		super(msg);
	}

	@Override
	public String getMessage() {
		return "No value present in the Optional instance";
	}
}
