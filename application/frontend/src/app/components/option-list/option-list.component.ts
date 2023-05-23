import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-option-list',
  templateUrl: './option-list.component.html',
  styleUrls: ['./option-list.component.css']
})
export class OptionListComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  logout(): void {
      localStorage.removeItem("token");
  }

}
