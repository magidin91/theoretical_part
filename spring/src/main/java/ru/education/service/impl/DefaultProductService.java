package ru.education.service.impl;

import org.springframework.stereotype.Service;
import ru.education.entity.Product;
import ru.education.entity.SalesPeriods;
import ru.education.exceptions.EntityAlreadyExistsException;
import ru.education.exceptions.EntityHasDetailsException;
import ru.education.exceptions.EntityIllegalArgumentException;
import ru.education.exceptions.EntityNotFoundException;
import ru.education.jpa.ProductRepository;
import ru.education.jpa.SalesPeriodsRepository;
import ru.education.service.ProductService;

import java.util.List;

@Service
public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;
    private final SalesPeriodsRepository salesPeriodsRepository;

    public DefaultProductService(ProductRepository productRepository, SalesPeriodsRepository salesPeriodsRepository) {
        this.productRepository = productRepository;
        this.salesPeriodsRepository = salesPeriodsRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Object id) {
        Product product;
        if (id == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Integer parsedId;
        try {
            parsedId = Integer.valueOf(id.toString());
        } catch (NumberFormatException ex) {
            throw new EntityIllegalArgumentException(String.format("Не удалось преобразовать идентификатор" +
                    " к нужному типу текст ошибки: %s", ex));
        }
        product = productRepository.findById(parsedId).orElse(null);
        if (product == null)
            throw new EntityNotFoundException(Product.Type_Name, parsedId);
        return product;
    }

    public Product create(Product product) {
        if (product == null) {
            throw new EntityIllegalArgumentException("Создавамый объект не может быть null");
        }
        if (product.getId() == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Product existedProduct = productRepository.findById(product.getId()).orElse(null);
        if (existedProduct != null) {
            throw new EntityAlreadyExistsException(Product.Type_Name, product.getId());
        }
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        if (product == null) {
            throw new EntityIllegalArgumentException("Создавамый объект не может быть null");
        }
        if (product.getId() == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Product existedProduct = productRepository.findById(product.getId()).orElse(null);
        if (existedProduct == null) {
            throw new EntityNotFoundException(Product.Type_Name, product.getId());
        }
        return productRepository.save(product);
    }

    public void delete(Object id) {
        Product product = findById(id);
        List<SalesPeriods> salesPeriods = salesPeriodsRepository.findByProduct(product);
        if (salesPeriods.size() > 0) {
            throw new EntityHasDetailsException(SalesPeriods.Type_Name, product.getId());
        }
        productRepository.delete(product);
    }
}
