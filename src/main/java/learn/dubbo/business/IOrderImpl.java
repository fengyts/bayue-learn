package learn.dubbo.business;

import learn.dubbo.domain.Order;

/**
 * <pre>
 * 订单实现类
 * </pre>
 *
 * @author fengyts
 * @version $Id: IOrderImpl.java, v 0.1 2019年5月2日 下午10:42:29 fengyts Exp $
 */
public class IOrderImpl implements IOrder {

	@Override
	public Order selectById(Long id) {
		return new Order(id, "java 高并发");
	}
}
