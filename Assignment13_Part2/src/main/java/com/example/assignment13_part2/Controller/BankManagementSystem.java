package com.example.assignment13_part2.Controller;

import com.example.assignment13_part2.ApiResponse.ApiResponse;
import com.example.assignment13_part2.Model.BankManagement;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank")
public class BankManagementSystem {
    ArrayList<BankManagement> bankAccounts = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<BankManagement> getBankAccounts(){
        return bankAccounts;
    }

    @PostMapping("/add")
    public ApiResponse postNewCustomer(@RequestBody BankManagement bankAccount){
        bankAccounts.add(bankAccount);
        return new ApiResponse("Customer : '"+bankAccount.getUsername()+"' added successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateAccount(@PathVariable int index , @RequestBody BankManagement account){
        bankAccounts.set(index, account);
        return new ApiResponse("Updated data of : '"+account.getUsername()+"' successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteAccount(@PathVariable int index){
        String remove_user = bankAccounts.get(index).getUsername();
        bankAccounts.remove(index);
        return new ApiResponse("Delete account of : '"+remove_user+"' successfully");
    }

    @PutMapping("/deposit/{index}")
    public ApiResponse deposit_Money(@PathVariable int index , @RequestBody BankManagement depositMoney){
        if (depositMoney.getBalance() > 0) {
            bankAccounts.get(index).setBalance((bankAccounts.get(index).getBalance() + depositMoney.getBalance()));
            return new ApiResponse("Customer: '" + bankAccounts.get(index).getUsername() + "' make deposit operation successfully");
        }else
            return new ApiResponse("You entered invalid money");
    }

    @PutMapping("/withdraw/{index}")
    public ApiResponse withdraw_Money(@PathVariable int index , @RequestBody BankManagement withdrawMoney){
        if(withdrawMoney.getBalance() < bankAccounts.get(index).getBalance() && withdrawMoney.getBalance() >0) {
            bankAccounts.get(index).setBalance((bankAccounts.get(index).getBalance() - withdrawMoney.getBalance()));
            return new ApiResponse("Customer: '" + bankAccounts.get(index).getUsername() + "' make withdraw operation successfully");
        }else {
            return new ApiResponse("Sorry can't do withdraw operation");
        }
    }

}
