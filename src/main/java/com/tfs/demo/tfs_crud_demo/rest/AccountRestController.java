package com.tfs.demo.tfs_crud_demo.rest;

import com.tfs.demo.tfs_crud_demo.entity.Account;
import com.tfs.demo.tfs_crud_demo.entity.Customer;
import com.tfs.demo.tfs_crud_demo.service.AccountService;
import com.tfs.demo.tfs_crud_demo.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {

    private AccountService accountService;
    private CustomerService customerService;

    @Autowired
    public AccountRestController(AccountService theAccountService, CustomerService theCustomerService){
        accountService = theAccountService;
        customerService = theCustomerService;
    }

    @GetMapping("/accounts")
    @ApiOperation("Return list of all accounts")
    public List<Account> getAllAccount(){
        return accountService.getAllAccounts();
    }

//    @GetMapping("/accounts/{accountId}")
//    @ApiOperation("Return account based on accountId")
//    public Account getAccountByAccountId(@PathVariable String accountId){
//        Account theAccount = accountService.getAccountById(accountId);
//        if(theAccount==null){
//            throw new RuntimeException("Account with id - " +accountId+ " not found!");
//        }
//        return theAccount;
//    }

    @PostMapping ("/accountsByUsername/{accountId}&{password}")
    public Customer checkLogin(@PathVariable String accountId, @PathVariable String password){
        Account theAccount = accountService.getAccountById(accountId);
        if(theAccount==null){
            return null;
        }
        if(!password.equals(theAccount.getPassword())){
            throw new RuntimeException("Wrong password");
        }
        return theAccount.getTheCustomer();
    }

    @PostMapping("/accountsByPhone/{phoneNumber}&{password}")
    public Customer checkLoginByPhone(@PathVariable String phoneNumber,@PathVariable String password){
        Account theAccount = accountService.checkLoginByPhone(phoneNumber);
        if(theAccount == null){
            throw new RuntimeException("Account not found!");
        }
        if(!theAccount.getPassword().equals(password)){
            throw new RuntimeException("Wrong password");
        }
        return theAccount.getTheCustomer();
    }

    @PostMapping("/accounts")
    @ApiOperation("Add new account (need full Account's JSON)")
    public String addNewAccount(@RequestBody Account theAccount){
        if(!accountService.CheckDuplicateAccountId(theAccount.getAccountId())){
            return "Duplicate with accountId: "+theAccount.getAccountId() +" has been found,please try again!";
        }
        if(!accountService.checkDuplicatePhoneNumber(theAccount.getPhoneNumber())){
            return "This phone number is already linked with another account, please try again!";
        }
        accountService.saveAccount(theAccount);
        return "Saved account: "+theAccount;
    }

    @PutMapping("/accounts")
    @ApiOperation("Update existing account (need full Account's JSON)")
    public Account updateAccount(@RequestBody Account theAccount){
        accountService.saveAccount(theAccount);
        return theAccount;
    }

    @DeleteMapping("/accounts/{accountId}")
    @ApiOperation("Disable existing account based on id")
    public String disableAccount(@PathVariable String accountId){
        Account theAccount = accountService.getAccountById(accountId);
        if(theAccount==null){
            throw new RuntimeException("Account with id - "+accountId+ " not found!");
        }
        accountService.disableAccount(accountId);
        return "Disable account with id - " +accountId+ " completed!";
    }
}
