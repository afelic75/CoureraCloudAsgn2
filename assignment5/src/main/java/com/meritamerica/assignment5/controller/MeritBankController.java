package com.meritamerica.assignment5.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.meritamerica.assignment5.AccountHolder;
import com.meritamerica.assignment5.MeritBank;
import Accounts.CheckingAccount;
import Exceptions.ExceedsCombinedBalanceLimitException;
import Exceptions.NegativeAmountException;
import Exceptions.NotFoundException;

@RestController
public class MeritBankController {
    @GetMapping("/AccountHolders")  
    public List<AccountHolder> ac() {
    	return MeritBank.getAccountHolders();
    }
    
    @PostMapping("/AccountHolders")
    public AccountHolder aac (@RequestBody @Valid AccountHolder ah) {
    	MeritBank.addAccountHolder(ah);
    	return ah;
    }
    @GetMapping("/AccountHolders/{id}")
    public AccountHolder x(@PathVariable(name="id")int id) throws NotFoundException{
    	AccountHolder y = MeritBank.getAH(id);
    	return y;
    }
    @PostMapping("AccountHolders/{id}/CheckingAccounts")
    @ResponseStatus(HttpStatus.CREATED) 
    public CheckingAccount ach (@PathVariable(name="id")int id,
    				@RequestBody @Valid CheckingAccount ach) throws NotFoundException, ExceedsCombinedBalanceLimitException, NegativeAmountException {
    	AccountHolder a = MeritBank.getAH(id);
    	a.addCheckingAccount(ach);
    	return ach;
    }
    @GetMapping("AccountHolders/{id}/CheckingAccounts")
    public List<CheckingAccount> gca(@PathVariable(name="id")int id) throws NotFoundException {
    	AccountHolder a = MeritBank.getAH(id);
    	return a.getCheckingAccounts();
    }
    
}
    		
    	
	  	
		
     
	
	
	

