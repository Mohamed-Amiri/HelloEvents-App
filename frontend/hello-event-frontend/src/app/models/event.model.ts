export interface Event {
    id?: number;
    title: string;
    description: string;
    date: Date;
    location: string;
    capacity: number;
    price: number;
    imageUrl?: string;
    organizerId?: number;
    createdAt?: Date;
    updatedAt?: Date;
} 