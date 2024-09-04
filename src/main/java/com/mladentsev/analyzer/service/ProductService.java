package com.mladentsev.analyzer.service;

import com.mladentsev.analyzer.model.dto.output.statistics.OutputPurchaseStatisticDTO;
import com.mladentsev.analyzer.repositories.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 ** Класс, вызывающий запросы в базу данных
 * и конвертирующий вернувшийся ответ в необходимый тип
 * для дальнейшей сборки конечного объекта output
 */

@RequiredArgsConstructor
@Service
public class ProductService {

    private final IProductRepository iProductRepository;

    /**
     ** Метод
     * @param id принимает id покупателя
     * @param startDate принимает начало периода
     * @param endDate принимает конец периода
     * @return возвращает список продуктов, которые купил конкретный покупатель id в заданный период startDate endDate
     */
    public List<OutputPurchaseStatisticDTO> findAllProductAndPriceByCustomerIdBetweenDate(Long id, Date startDate, Date endDate) {
        return iProductRepository.findAllProductAndPriceByCustomerIdBetweenDate(id, startDate, endDate)
                .stream()
                .map(entity -> new OutputPurchaseStatisticDTO(entity.getName(), entity.getPrice()))
                .collect(Collectors.toList());
    }
}
