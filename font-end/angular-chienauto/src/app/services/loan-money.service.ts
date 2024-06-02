import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoanMoneyService {
  private loanDuration: number = 0; // Tháng vay
  private downPayment: number = 0; // Phần trăm trả trước
  private interestRate: number = 0; // Lãi suất
  private loanMoney: number = 0; // Tiền vay

  constructor() { 
    this.loadFromLocalStorage();
  }

  // Getter & setter loanDuration
  setLoanDuration(duration: number) {
    this.loanDuration = duration;
    this.saveToLocalStorage();
  }
  getLoanDuration() {
    return this.loanDuration;
  }

  // Getter & setter interestRate
  setInterestRate(interestRate: number) {
    this.interestRate = interestRate;
    this.saveToLocalStorage();
  }
  getInterestRate() {
    return this.interestRate;
  }

  //getter & setter downPayment
  setDownPayment(downPayment: number) {
    this.downPayment = downPayment;
    this.saveToLocalStorage();
  }
  getDownPayment() {
    return this.downPayment;
  }

  // Getter & setter loanMoney
  setLoanAmount(loanAmount: number){
    this.loanMoney = loanAmount;
    this.saveToLocalStorage();
  }
  getLoanAmount(){
    return this.loanMoney;
  }

  // Tính giá sau khi ưu đãi
  calculatePriceAfterDiscount(unitPrice: number): number {
    return unitPrice * 0.99;
  }

  // Tính tiền sau khi trả trước
  calculateDownPayment(priceAfterDiscount: number, downPayment: number): number {
    return priceAfterDiscount * downPayment / 100;
  }

  // Tính tiền vay
  calculateLoanAmount(priceAfterDiscount: number, downPayment: number): number {
    return priceAfterDiscount - this.calculateDownPayment(priceAfterDiscount, downPayment);
  }

   //tính tiền lãi
   calculateInterestAmount(loanAmount: number, interestRate: number): number {
    return loanAmount * interestRate / 100;
  }
  // Tính trả nợ gốc hàng tháng
  tinhTraNoGoc(loanAmount: number, loanDuration: number): number {
    return loanAmount / loanDuration;
  }
  //tính tổng tiền vay
  calculateTotalAmount(loanAmount: number, interestAmount: number): number {
    return loanAmount + interestAmount;
  }
  // Tính trả lãi hàng tháng
  tinhTraLai(loanAmount: number, interestRate: number, loanDuration: number): number {
    return loanAmount * (interestRate / 100) / loanDuration;
  }

  // Tạo lịch thanh toán
  createLoanSchedule(loanAmount: number, interestRate: number, loanDuration: number) {
    const schedule = [];
    let remainingBalance = loanAmount;

    for (let month = 1; month <= loanDuration; month++) {
      const monthlyPrincipal = this.tinhTraNoGoc(loanAmount, loanDuration);
      const monthlyInterest = this.tinhTraLai(remainingBalance, interestRate, loanDuration);
      const totalPayment = monthlyPrincipal + monthlyInterest;
      const beginningBalance = remainingBalance;
      remainingBalance -= monthlyPrincipal;

      schedule.push({
        month,
        beginningBalance,
        principalPayment: monthlyPrincipal,
        interestPayment: monthlyInterest,
        totalPayment,
        endingBalance: remainingBalance
      });
    }
    return schedule;
  }
  private isLocalStorageAvailable(): boolean {
    return typeof localStorage !== 'undefined';
  }
  private saveToLocalStorage() {
    if (this.isLocalStorageAvailable()) {
      localStorage.setItem('loanDuration', this.loanDuration.toString());
      localStorage.setItem('downPayment', this.downPayment.toString());
      localStorage.setItem('interestRate', this.interestRate.toString());
      localStorage.setItem('loanMoney', this.loanMoney.toString());
    }
  }

  private loadFromLocalStorage() {
    if (this.isLocalStorageAvailable()) {
      const loanDuration = localStorage.getItem('loanDuration');
      const downPayment = localStorage.getItem('downPayment');
      const interestRate = localStorage.getItem('interestRate');
      const loanMoney = localStorage.getItem('loanMoney');

      if (loanDuration) {
        this.loanDuration = +loanDuration;
      }
      if (downPayment) {
        this.downPayment = +downPayment;
      }
      if (interestRate) {
        this.interestRate = +interestRate;
      }
      if (loanMoney) {
        this.loanMoney = +loanMoney;
      }
    }
  }

}
