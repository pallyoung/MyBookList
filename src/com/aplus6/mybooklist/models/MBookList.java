package com.aplus6.mybooklist.models;

public class MBookList {
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getC_time() {
		return c_time;
	}
	public void setC_time(long c_time) {
		this.c_time = c_time;
	}
	public long getM_time() {
		return m_time;
	}
	public void setM_time(long m_time) {
		this.m_time = m_time;
	}
	private long id;
	private String name;
	private long c_time;
	private long m_time;

}
