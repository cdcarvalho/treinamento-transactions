package br.com.itau.transactions.api;

import br.com.itau.transactions.dto.TransactionDTO;
import br.com.itau.transactions.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService service;

    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public TransactionDTO createSuccess(@RequestBody TransactionDTO request) {
        return service.create(request);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/create-requires-new")
    public TransactionDTO createRequiresNew(@RequestBody TransactionDTO request) {
        return service.createRequiresNew(request);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/private-method")
    public TransactionDTO privateMethod(@RequestBody TransactionDTO request) {
        return service.createPrivateMethod(request);
    }

    @PostMapping("/no-rollback")
    public TransactionDTO noRollback(@RequestBody TransactionDTO request) {
        return service.noRollback(request);
    }

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    public TransactionDTO get(@PathVariable("id") Long id) {
        return service.get(id);
    }


    @ResponseStatus(CREATED)
    @PostMapping("/create-parallel")
    public void createParallel(@RequestBody TransactionDTO request) {
        service.createParallel(request);
    }
}
