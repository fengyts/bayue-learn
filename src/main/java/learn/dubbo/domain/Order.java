package learn.dubbo.domain;

import java.io.Serializable;

public class Order implements Serializable {

	private static final long serialVersionUID = -7655696448996801166L;

	private Long id;
	private String desc;

	public Order() {
		super();
	}

	public Order(Long id, String desc) {
		super();
		this.id = id;
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", desc=" + desc + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
