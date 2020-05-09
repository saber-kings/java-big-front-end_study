package com.saberking.mydatastructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义队列，可以指定队列中元素类型
 * 
 * @author luanz
 *
 * @param <E> 泛型
 */
public class MyQueue<E> implements Queue<E> {

	/**
	 * 队头节点
	 */
	private Node<E> first;
	/**
	 * 队尾节点
	 */
	private Node<E> last;
	/**
	 * 队列长度
	 */
	private int num;

	public MyQueue() {
		this.first = null;
		this.last = null;
		this.num = 0;
	}

	/**
	 * 返回队列长度
	 */
	@Override
	public int size() {
		return this.num;
	}

	/**
	 * 判断队列是否为空
	 */
	@Override
	public boolean isEmpty() {
		if (this.num == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断队列中是否有给定元素
	 */
	@Override
	public boolean contains(Object o) {
		return this.getList().contains(o);
	}

	/**
	 * 返回一个迭代器，这里其实是先将队列转换成了list集合再返回集合的迭代器， 不建议使用这个方法，推荐直接使用getList将队列转换成集合
	 */
	@Override
	public Iterator<E> iterator() {
		return this.getList().iterator();
	}

	/**
	 * 将队列转换成 Object 数组并返回
	 */
	@Override
	public Object[] toArray() {
		return this.getList().toArray();
	}

	/**
	 * 将队列转换成其内元素类型的数组并返回，需要先传递一个数组对象
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return this.getList().toArray(a);
	}

	/**
	 * 移除队列队头的元素并返回移除是否成功
	 */
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

	/**
	 * 判断队列中是否包含给定集合中的所有元素
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return this.getList().containsAll(c);
	}

	/**
	 * 向队列中添加给定集合中的所有元素
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		try {
			c.forEach(item -> {
				this.add(item);
			});
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 从队列中移除给定集合中的所有元素
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		try {
			List<E> list = this.getList();
			c.forEach(item -> {
				list.remove(item);
			});
			this.clear();
			this.addAll(list);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 判断队列与给定集合是否包含相同的对象或元素即取交集
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		return this.getList().retainAll(c);
	}

	/**
	 * 清空队列
	 */
	@Override
	public void clear() {
		this.first = null;
		this.last = null;
		this.num = 0;
	}

	/**
	 * 向队列中添加元素
	 */
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

	/**
	 * 向队列中添加元素，如果为空则直接抛出空指针异常
	 */
	@Override
	public boolean offer(E e) {
		checkNotNull(e);
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

	/**
	 * 如果给定元素指向对象为空，则抛出空指针异常
	 *
	 * @param v 给定元素
	 */
	private static void checkNotNull(Object v) {
		if (v == null) {
			throw new NullPointerException("给定元素为空");
		}
	}

	/**
	 * 移除队列队头的元素并返回，如果队列已空则抛出队列已空不能移除元素的异常
	 */
	@Override
	public E remove() {
		if (!isEmpty()) {
			Node<E> n = this.first;
			this.first = this.first.getNext();
			n.setNext(null);
			this.num--;
			return n.getData();
		} else {
			throw new NoSuchElementException("队列已空不能移除");
		}
	}

	/**
	 * 从队列中取出一个元素，先进先出，即取出队头
	 */
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

	/**
	 * 将队列转成List的集合并返回即得到一个包含队列中所有元素的list集合
	 * 
	 * @return list 集合
	 */
	public List<E> getList() {
		try {
			if (!isEmpty()) {
				List<E> list = new ArrayList<>();
				Node<E> n = this.first;
				while (true) {
					if (this.first.next == null) {
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
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("队列转换集合失败");
			return null;
		}
	}

	/**
	 * 返回队列的队头，并不移除，当队列为空时抛出异常
	 */
	@Override
	public E element() {
		if (!isEmpty()) {
			return this.first.getData();
		} else {
			throw new NoSuchElementException("队列为空，没有头部元素");
		}
	}

	/**
	 * 返回队列的队头，并不移除，当队列为空返回空
	 */
	@Override
	public E peek() {
		if (!isEmpty()) {
			return this.first.getData();
		} else {
			return null;
		}
	}

	/**
	 * 队列当中的每一个元素的封装内部类
	 * 
	 * @author luanz
	 * @param <T> 泛型，用于指定队列中的元素类型
	 */
	@Getter
	@Setter
	private class Node<T> {
		// 数据域
		private E data;
		// 指针域，指向下一个
		private Node<E> next;
	}

}
