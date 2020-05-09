package com.saberking.mydatastructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

import lombok.Getter;
import lombok.Setter;

public class MyQueue<E> implements Queue<E> {

	private Node<E> first;
	private Node<E> last;
	private int num;

	public MyQueue() {
		this.first = null;
		this.last = null;
		this.num = 0;
	}

	@Override
	public int size() {
		return this.num;
	}

	@Override
	public boolean isEmpty() {
		if (this.num == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(Object o) {
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public boolean remove(Object o) {
		if (!isEmpty()) {
			Node<E> n = this.first;
			this.first = this.first.getNext();
			n.setNext(null);
			this.num--;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public  boolean containsAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		try {
			c.forEach(item -> {
				this.add(item);
			});
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {
		this.first = null;
		this.last = null;
		this.num = 0;
	}

	@Override
	public boolean add(E e) {
		try {
			Node<E> n = new Node<E>();
			n.setData(e);
			if (first == null) {
				first = n;
				last = n;
			} else {
				// last在重新指向新的节点之前，将上一个节点的下一个引用指向新的节点
				last.setNext(n);
				last = n;
			}
			this.num++;
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean offer(Object e) {
		return false;
	}

	@Override
	public E remove() {
		if (!isEmpty()) {
			Node<E> n = this.first;
			this.first = this.first.getNext();
			n.setNext(null);
			this.num--;
			return n.getData();
		} else {
			throw new NoSuchElementException("队列已空不能删除");
		}
	}

	@Override
	public E poll() {
		if (!isEmpty()) {
			Node<E> n = this.first;
			this.first = this.first.getNext();
			n.setNext(null);
			this.num--;
			return n.getData();
		} else {
			return null;
		}
	}

	public List<E> getList() {
		if (!isEmpty()) {
			List<E> list = new ArrayList<>();
			Node<E> n = this.first;
			while(true) {
				if (this.first.next==null) {
					list.add(this.first.data);
					break;
				}
				list.add(this.first.data);
				this.first = this.first.getNext();
				
			}
			this.first = n;
			return list;
		} else {
			return null;
		}
	}

	@Override
	public E element() {
		if (!isEmpty()) {
			return this.first.getData();
		} else {
			throw new NoSuchElementException("队列为空，没有头部元素");
		}
	}

	@Override
	public E peek() {
		if (!isEmpty()) {
			return this.first.getData();
		} else {
			return null;
		}
	}

	@Getter
	@Setter
	private class Node<T> {
		private E data;
		private Node<E> next;
	}

}
