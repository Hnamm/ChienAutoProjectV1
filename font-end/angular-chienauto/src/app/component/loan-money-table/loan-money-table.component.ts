import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { LoanMoneyService } from '../../services/loan-money.service';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';
@Component({
  selector: 'app-loan-money-table',
  templateUrl: './loan-money-table.component.html',
  styleUrls: ['./loan-money-table.component.css']
})
export class LoanMoneyTableComponent implements OnInit {
  loanDuration!: number;
  downPayment!: number;
  interestRate!: number;
  loanAmount!: number;
  loanSchedule: any[] = [];

  constructor(private loanMoneyService: LoanMoneyService) {}

  ngOnInit(): void {
    this.loanDuration = this.loanMoneyService.getLoanDuration();
    this.interestRate = this.loanMoneyService.getInterestRate();
    this.loanAmount = this.loanMoneyService.getLoanAmount();
    this.generateLoanSchedule();
  }

  generateLoanSchedule() {
    console.log('Generating loan schedule with:', this.loanAmount, this.interestRate, this.loanDuration);
    this.loanSchedule = this.loanMoneyService.createLoanSchedule(this.loanAmount, this.interestRate, this.loanDuration);
    console.log('Loan schedule:', this.loanSchedule);
  }


  @ViewChild('content', { static: false }) content!: ElementRef;

  downloadPDF() {
    const content: HTMLElement | null = document.getElementById('content');

    if (!content) {
        console.error('Element reference is undefined.');
        return;
    }

    html2canvas(content).then((canvas) => {
      const imgData = canvas.toDataURL('image/png');
      const pdf = new jsPDF();
      const imgWidth = 210;
      const imgHeight = canvas.height * imgWidth / canvas.width;

      pdf.addImage(imgData, 'PNG', 0, 0, imgWidth, imgHeight);
      pdf.save('loan_schedule.pdf');
    });
}

}
