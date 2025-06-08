import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from '../../services/event.service';
import { Event } from '../../models/event.model';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.component.html'
})
export class EventDetailComponent implements OnInit {
  event: Event | null = null;
  loading = false;
  error = '';
  isOrganizer = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private eventService: EventService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.loadEvent();
  }

  loadEvent(): void {
    this.loading = true;
    const eventId = Number(this.route.snapshot.paramMap.get('id'));
    
    this.eventService.getEventById(eventId).subscribe({
      next: (event) => {
        this.event = event;
        this.checkIfOrganizer();
        this.loading = false;
      },
      error: (error) => {
        this.error = 'Error loading event details. Please try again later.';
        this.loading = false;
        console.error('Error loading event:', error);
      }
    });
  }

  checkIfOrganizer(): void {
    this.authService.currentUser$.subscribe(currentUser => {
      if (currentUser && this.event) {
        this.isOrganizer = currentUser.id === this.event.organizerId;
      }
    });
  }

  editEvent(): void {
    if (this.event) {
      this.router.navigate(['/events', this.event.id, 'edit']);
    }
  }

  deleteEvent(): void {
    if (this.event && confirm('Are you sure you want to delete this event?')) {
      this.eventService.deleteEvent(this.event.id!).subscribe({
        next: () => {
          this.router.navigate(['/events']);
        },
        error: (error) => {
          this.error = 'Error deleting event. Please try again later.';
          console.error('Error deleting event:', error);
        }
      });
    }
  }

  goBack(): void {
    this.router.navigate(['/events']);
  }
} 