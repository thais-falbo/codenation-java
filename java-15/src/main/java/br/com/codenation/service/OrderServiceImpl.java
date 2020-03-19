package br.com.codenation.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private ProductRepository productRepository = new ProductRepositoryImpl();
	private final Integer DISCOUNT = 20;

	/**
	 * Calculate the sum of all OrderItems
	 */
	@Override
	public Double calculateOrderValue(List<OrderItem> items) {
		return items.stream()
				.map(item -> this.calculateOrderItemValue(item))
				.reduce(0.0, Double::sum);
	}

	/**
	 * Map from idProduct List to Product Set
	 */
	@Override
	public Set<Product> findProductsById(List<Long> ids) {
		return ids.stream()
				.map(id -> productRepository.findById(id).orElse(null))
				.filter(Objects::nonNull)
				.collect(Collectors.toSet());
	}

	/**
	 * Calculate the sum of all Orders(List<OrderItem>)
	 */
	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
		return orders.stream()
				.map(orderItems -> this.calculateOrderValue(orderItems))
				.reduce(0.0, Double::sum);
	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
		return productIds.stream()
				.map(id -> productRepository.findById(id).get())
				.collect(Collectors.groupingBy(Product::getIsSale));
	}

	private Double calculateOrderItemValue (OrderItem item) {
		Product product = productRepository.findById(item.getProductId()).get();

		Double value = product.getValue() * item.getQuantity();

		if (product.getIsSale()) value -= (value * DISCOUNT) / 100;

		return value;
	}
}