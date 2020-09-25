import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

//OnInit is hook that is called after initialized
export class AppComponent implements OnInit {
  // define list of items
  items = [];
  events = [];
  time = new Date();
  intervals = [];
  Times = [];
  comItems = [];


  ngOnInit(): void {

  }

  // Write code to push new item
  submitNewItem(event) {
    const index: number = this.items.indexOf(event.taskName);
    if (index !== -1) {
      alert('Item already exists');
    } else {
      this.items.push(event.taskName);
    }

    const eventObject = {
      id: this.events.length + 1,
      name: event.taskName,
      expectedDate: new Date(event.time),
      completed: false,
    };
    this.Countdown(eventObject.expectedDate, this.events.length);
    this.events.push(eventObject);
  }

  // delete item if it is completed
  deleteItem(item: string) {
    //assign index of item
    let index: number = this.items.indexOf(item);
    if (index !== -1) {
      this.items.splice(index, 1);
    } else {
      index = this.comItems.indexOf(item);
      if (index !== -1) {
        this.comItems.splice(index, 1);
      }
    }
  }

  //countdown function for countdown timer
  Countdown(eventTime, index) {
    //get time and assign it to time list
    this.intervals[index] = setInterval(() => {
      const now = new Date().getTime();
      const difference = eventTime.getTime() - now;
      const days = Math.floor(difference / (1000 * 60 * 60 * 24));
      const hours = Math.floor((difference % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
      const minutes = Math.floor((difference % (1000 * 60 * 60)) / (1000 * 60));
      const seconds = Math.floor((difference % (1000 * 60)) / 1000);
      this.Times[index] = {
        days,
        hours,
        minutes,
        seconds
      };
    }, 1000);
  }


}
