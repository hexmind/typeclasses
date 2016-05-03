package pl.hexmind.tc.order

import static pl.hexmind.tc.order.Order.CanceledOrder
import static pl.hexmind.tc.order.Order.ComplexOrder
import static pl.hexmind.tc.order.Order.GeneralOrder

trait OrderFixture implements ProductFixture {
    Order cost100only = new GeneralOrder('', [productFor100])
    Order cost100and200 = new GeneralOrder('', [productFor100, productFor200])
    Order canceled = new CanceledOrder(cost100only)
    Order cost100x2and200 = new ComplexOrder([cost100only, cost100and200])
    Order cost100x2and200allDoubled = new ComplexOrder([cost100x2and200, cost100x2and200])
    Order cost100x2and200andCanceled = new ComplexOrder([cost100x2and200, canceled])
}
