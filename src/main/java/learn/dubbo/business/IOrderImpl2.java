package learn.dubbo.business;

import learn.dubbo.domain.Order;

public class IOrderImpl2 implements IOrder {

	@Override
	public Order selectById(Long id) {
		return new Order(id, "dubbo 高并发架构师");
	}

}
