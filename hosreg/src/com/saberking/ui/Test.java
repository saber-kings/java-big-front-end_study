package com.saberking.ui;

public class Test {

	public static void main(String[] args) {
		Node first = null;
		Node last = null;
		for (int i = 0; i < 10; i++) {
			Node n = new Node();
			n.setData(i);
			if (first == null) {
				first = n;
				last = n;
			} else {
				//last在重新指向新的节点之前，将上一个节点的下一个引用指向新的节点
				last.setNext(n);
				last = n;
			}
		}

	}

}
