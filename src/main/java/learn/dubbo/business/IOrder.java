package learn.dubbo.business;

import learn.dubbo.domain.Order;

public interface IOrder {

	public Order selectById(Long id);

}
