package com.saberking.mydatastructure;

public class Test {

	public static void main(String[] args) {
		MyQueue<String> myQueue = new MyQueue<>();
		for (int i = 0; i < 10; i++) {
			myQueue.add("aaaa: "+i);
		}
//		int size = myQueue.size();
//		for (int i = 0; i < size; i++) {
//			System.out.println(myQueue.poll());
//		}
		while (myQueue.size()>0) {
			System.out.println(myQueue.poll());
		}
//		Node first = null;
//		Node last = null;
//		for (int i = 0; i < 10; i++) {
//			Node n = new Node();
//			n.setData(i);
//			if (first == null) {
//				first = n;
//				last = n;
//			} else {
//				//last在重新指向新的节点之前，将上一个节点的下一个引用指向新的节点
//				last.setNext(n);
//				last = n;
//			}
//		}

	}

}
