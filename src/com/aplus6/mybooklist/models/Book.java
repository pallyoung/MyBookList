package com.aplus6.mybooklist.models;

public class Book {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public long getC_time() {
		return c_time;
	}
	public void setC_time(long c_time) {
		this.c_time = c_time;
	}
	public long getLast_read_time() {
		return last_read_time;
	}
	public void setLast_read_time(long last_read_time) {
		this.last_read_time = last_read_time;
	}
	public long getProcess() {
		return process;
	}
	public void setProcess(long process) {
		this.process = process;
	}
	public long getP_count() {
		return p_count;
	}
	public void setP_count(long p_count) {
		this.p_count = p_count;
	}
	public long getH_p_count() {
		return h_p_count;
	}
	public void setH_p_count(long h_p_count) {
		this.h_p_count = h_p_count;
	}
	private String name;
	private String author;
	private long c_time;
	private long last_read_time;
	private long process;
	private long p_count;
	private long h_p_count;
}
