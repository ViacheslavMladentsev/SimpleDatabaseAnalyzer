package com.mladentsev.analyzer.service;

import com.mladentsev.analyzer.repositories.IPurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 ** Класс, вызывающий запросы в базу данных
 * и конвертирующий вернувшийся ответ в необходимый тип
 * для дальнейшей сборки конечного объекта output
 */

@RequiredArgsConstructor
@Service
public class PurchaseService {
    private final IPurchaseRepository iPurchaseRepository;

    /**
     ** Метод
     * @param startDate принимает начало периода
     * @param endDate принимает конец периода
     * @return возвращает список id уникальных  покупателей, совершивших покупки в заданный период startDate endDate
     */
    public List<Long> findDistinctIdByDateTimeBetween(Date startDate, Date endDate) {
        return iPurchaseRepository.findDistinctIdByDateTimeBetween(startDate, endDate);
    }

    /**
     ** Метод
     * @param startDate принимает начало периода
     * @param endDate принимает конец периода
     * @return возвращает общую стоимость всех покупок совершенных в заданном периоде startDate endDate
     */
    public Double findTotalPriceAllPurchasesBetweenDate(Date startDate, Date endDate) {
        return iPurchaseRepository.findTotalPriceAllPurchasesBetweenDate(startDate, endDate);
    }


}
