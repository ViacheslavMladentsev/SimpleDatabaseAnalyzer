package com.mladentsev.analyzer.service;

import com.mladentsev.analyzer.model.dto.output.search.OutputCustomerSearchDTO;
import com.mladentsev.analyzer.model.entity.PurchaseEntity;
import com.mladentsev.analyzer.repositories.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 ** Класс, вызывающий запросы в базу данных
 * и конвертирующий вернувшийся ответ в необходимый тип
 * для дальнейшей сборки конечного объекта output
 */

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final ICustomerRepository iCustomerRepository;

    /**
     ** Метод
     * @param  lastName принимает фамилию покупателя для поиска в базе
     * @return возвращает список покупателей с фамилией lastName
     */
    public List<OutputCustomerSearchDTO> findAllByLastName(String lastName) {
        return iCustomerRepository.findAllByLastName(lastName)
                .stream()
                .map(entity -> new OutputCustomerSearchDTO(entity.getName(), entity.getLastName()))
                .collect(Collectors.toList());
    }

    /**
     ** Метод
     * @param  title принимает название продукта
     * @param  count принимает минимальное количество покупок продукта title
     * @return возвращает список покупателей, которые купили title не менее count раз
     */
    public List<OutputCustomerSearchDTO> findAllByTitleAndCount(String title, int count) {
        return iCustomerRepository.findAllByTitleAndCount(title, count)
                .stream()
                .map(entity -> new OutputCustomerSearchDTO(entity.getName(), entity.getLastName()))
                .collect(Collectors.toList());
    }

    /**
     ** Метод
     * @param  min принимает минимальное количество потраченных денег
     * @param  max принимает максимальное количество потраченных денег
     * @return возвращает список покупателей потративших денег из диапазона min max
     */
    public List<OutputCustomerSearchDTO> findCustomersWithTotalPurchaseCostInRange(Integer min, Integer max) {
        return iCustomerRepository.findCustomersWithTotalPurchaseCostInRange(min, max)
                .stream()
                .map(entity -> new OutputCustomerSearchDTO(entity.getName(), entity.getLastName()))
                .collect(Collectors.toList());
    }

    /**
     ** Метод
     * @param  countCustomers количество покупателей
     * @return возвращает список из countCustomers покупателей, совершивших наименьшее количество покупок
     */
    public List<OutputCustomerSearchDTO> findCustomersWithLeastNumberOfPurchases(Integer countCustomers) {
        return iCustomerRepository.findCustomersWithLeastNumberOfPurchases(countCustomers)
                .stream()
                .map(entity -> new OutputCustomerSearchDTO(entity.getName(), entity.getLastName()))
                .collect(Collectors.toList());
    }

    /**
     ** Метод
     * @param id принимает id покупателя
     * @return возвращает полное имя (имя + фамилия) покупателя
     */
    public String findFullNameById(Long id) {
        return iCustomerRepository.findFullNameById(id);
    }

}
