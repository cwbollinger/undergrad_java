package edu.ncsu.csc216.searching_sorting.bst;

import java.util.NoSuchElementException;

public class BinarySearchTree {
	
	private IntTreeNode overallRoot;
	
	public BinarySearchTree() {
		overallRoot = null;
	}
	
	public void add(int value) {
		overallRoot = add(overallRoot, value);
	}
	
	private IntTreeNode add(IntTreeNode root, int value) {
		if (root == null) {
			root = new IntTreeNode(value);
		} else if (root.data > value) {
			root.left = add(root.left, value);
		} else if (root.data < value) {
			root.right = add(root.right, value);
		} //else duplicate
		return root;
	}
	
	public String preOrderTraversal() {
		return preOrderTraversal(overallRoot);
	}
	
	private String preOrderTraversal(IntTreeNode root) {
		String traversal = "";
		if (root != null) {
			traversal += root.data + " ";
			traversal += preOrderTraversal(root.left);
			traversal += preOrderTraversal(root.right);
		}
		return traversal;
	}
	
	public String inOrderTraversal() {
		return inOrderTraversal(overallRoot);
	}
	
	private String inOrderTraversal(IntTreeNode root) {
		String traversal = "";
		if (root != null) {
			traversal += inOrderTraversal(root.left);
			traversal += root.data + " ";
			traversal += inOrderTraversal(root.right);
		}
		return traversal;
	}
	
	public String postOrderTraversal() {
		return postOrderTraversal(overallRoot);
	}
	
	private String postOrderTraversal(IntTreeNode root) {
		String traversal = "";
		if (root != null) {
			traversal += postOrderTraversal(root.left);
			traversal += postOrderTraversal(root.right);
			traversal += root.data + " ";
		}
		return traversal;
	}
	
	public boolean contains(int value) {
		return contains(overallRoot, value);
	}
	
	private boolean contains(IntTreeNode root, int value) {
		if (root == null) {
			return false;
		} else if (root.data == value) {
			return true;
		} else if (root.data > value) {
			return contains(root.left, value);
		} else {
			return contains(root.right, value);
		}
	}
	
	public void remove(int value) {
		overallRoot = remove(overallRoot, value);
	}
	
	private IntTreeNode remove(IntTreeNode root, int value) {
		if (root == null) {
			return null;
		} else if (root.data > value) {
			root.left = remove(root.left, value);
		} else if (root.data < value) {
			root.right = remove(root.right, value);
		} else { //root.data == value - remove
			if (root.right == null) {
				//no R child, return left
				//if left is null, case of no children handled
				return root.left; 
			} else if (root.left == null) {
				//no L child, return right
				return root.right; 
			} else {
				//both children; replace w/ min from R
				root.data = getMin(root.right);
				root.right = remove(root.right, root.data);
			}
		}
		return root;
	}
	
	public int getMin() {
		if (overallRoot == null) {
			throw new NoSuchElementException();
		}
		return getMin(overallRoot);
	}
	
	private int getMin(IntTreeNode root) {
		if (root.left == null) {
			return root.data;
		} else {
			return getMin(root.left);
		}
	}
	
	
	
	private class IntTreeNode {
		public int data;
		public IntTreeNode left;
		public IntTreeNode right;
		
		public IntTreeNode(int data) {
			this(data, null, null);
		}
		
		public IntTreeNode(int data, IntTreeNode left, IntTreeNode right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

}
