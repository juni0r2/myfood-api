package br.com.myfood.myfoodapi.core.data;

import com.google.common.collect.ImmutableMap;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

public class PageableTranslator {
    public static Pageable translate(Pageable pageable, ImmutableMap<String, String> fieldsMappings) {

        var orders = pageable.getSort().stream()
                .filter(order -> fieldsMappings.containsKey(order.getProperty()))
                .map((order -> new Sort.Order(order.getDirection(), fieldsMappings.get(order.getProperty()))))
                .collect(Collectors.toList());

        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
    }
}
