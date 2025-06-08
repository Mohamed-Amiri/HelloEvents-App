import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  stats = {
    total: 12,
    upcoming: 5,
    past: 6,
    full: 1
  };

  recentEvents = [
    {
      id: 1,
      title: 'Angular Conference',
      date: new Date(),
      location: 'New York',
      imageUrl: '',
      capacity: 100
    },
    {
      id: 2,
      title: 'Spring Boot Meetup',
      date: new Date(),
      location: 'San Francisco',
      imageUrl: '',
      capacity: 0
    }
  ];

  constructor(private router: Router) {}

  ngOnInit(): void {}

  goToCreateEvent() {
    this.router.navigate(['/events/new']);
  }

  goToEvents() {
    this.router.navigate(['/events']);
  }
} 