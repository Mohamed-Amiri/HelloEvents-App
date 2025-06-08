import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from '../../services/event.service';
import { Event } from '../../models/event.model';

@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html'
})
export class EventFormComponent implements OnInit {
  eventForm: FormGroup;
  isEditMode = false;
  loading = false;
  error = '';
  eventId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private eventService: EventService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.eventForm = this.fb.group({
      title: ['', [Validators.required, Validators.minLength(3)]],
      description: ['', [Validators.required, Validators.minLength(10)]],
      date: ['', Validators.required],
      location: ['', Validators.required],
      capacity: ['', [Validators.required, Validators.min(1)]],
      price: ['', [Validators.required, Validators.min(0)]],
      imageUrl: ['']
    });
  }

  ngOnInit(): void {
    this.eventId = Number(this.route.snapshot.paramMap.get('id'));
    this.isEditMode = !!this.eventId;

    if (this.isEditMode) {
      this.loadEvent();
    }
  }

  loadEvent(): void {
    if (this.eventId) {
      this.loading = true;
      this.eventService.getEventById(this.eventId).subscribe({
        next: (event) => {
          this.eventForm.patchValue({
            title: event.title,
            description: event.description,
            date: new Date(event.date).toISOString().split('T')[0],
            location: event.location,
            capacity: event.capacity,
            price: event.price,
            imageUrl: event.imageUrl
          });
          this.loading = false;
        },
        error: (error) => {
          this.error = 'Error loading event. Please try again later.';
          this.loading = false;
          console.error('Error loading event:', error);
        }
      });
    }
  }

  onSubmit(): void {
    if (this.eventForm.valid) {
      this.loading = true;
      const eventData: Event = this.eventForm.value;

      const request = this.isEditMode
        ? this.eventService.updateEvent(this.eventId!, eventData)
        : this.eventService.createEvent(eventData);

      request.subscribe({
        next: () => {
          this.router.navigate(['/events']);
        },
        error: (error) => {
          this.error = `Error ${this.isEditMode ? 'updating' : 'creating'} event. Please try again later.`;
          this.loading = false;
          console.error(`Error ${this.isEditMode ? 'updating' : 'creating'} event:`, error);
        }
      });
    }
  }

  getErrorMessage(controlName: string): string {
    const control = this.eventForm.get(controlName);
    if (control?.hasError('required')) {
      return 'This field is required';
    }
    if (control?.hasError('minlength')) {
      return `Minimum length is ${control.errors?.['minlength'].requiredLength} characters`;
    }
    if (control?.hasError('min')) {
      return `Minimum value is ${control.errors?.['min'].min}`;
    }
    return '';
  }
} 