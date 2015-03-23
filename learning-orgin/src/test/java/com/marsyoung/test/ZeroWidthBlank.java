package com.marsyoung.test;

/**
 * @author Mars
 *         竟然说第二条语句有问题，表面上完全看不出来任何问题是不是！
 *         实际上这里的错误原因涉及到一个概念 —
 *         零宽度空格，可能有人接触过，但相信更多的人甚至都没听过，什么是零宽度空格？它实际上是一个Unicode字符，是一个空格，关键是它没有宽度，因此我们一般肉眼看不到。但可以在vim下看到
 *         ，上面的第二条语句中的2前面就有一个零宽度空格，放到vim中打开后你会发现是下面这样的语句：
 * 
 *         System.out.println(Integer.parseInt("<feff>2"));
 *         Unicode规范中定义，每一个文件的最前面分别加入一个表示编码顺序的字符，这个字符的名字叫做”零宽度非换行空格“（ZEROWIDTHNO-BREAKSPACE），用FEFF表示。这正好是两个字节，而且FF比FE大1。
 *         因此下面的语句会输出65279，刚好是FEFF。
 * 
 *         System.out.println((int)"2".charAt(0));
 */
public class ZeroWidthBlank {

	public static void main(String[] args) {
		System.out.println(Integer.valueOf("1"));
		System.out.println(Integer.parseInt("﻿2"));
	}
}
