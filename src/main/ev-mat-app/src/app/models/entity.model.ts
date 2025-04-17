export interface Notebook {
  id?: number;
  title?: string;
  events?: Event[];
}

export interface Event {
  id?: number;
  eventName: string;
}

export interface Detail {
  id?: number;
  dateOfEvent: Date | null | undefined;
  startTime: Date | null | undefined;
  endTime: Date | null | undefined;
  description: string | undefined;
}

export interface Performer {
  id?: number;
  fullName: string | undefined;
  moniker: string | undefined;
  email: string | undefined;
  performanceFee: number | null | undefined;
}

export interface Location {
  id?: number;
  locationName: string | undefined;
  phoneNumber: string | undefined;
  address: string | undefined;
  address2?: string;
  city: string | undefined;
  state: string | undefined;
  zip: string | undefined;
  website: string | undefined;
  accessible: boolean | undefined;
}

export interface Goal {
  id?: number;
  goalDescription: string | undefined;
}

export interface Note {
  id?: number;
  noteDescription: string | undefined;
}

export interface Reflection {
  id?: number;
  reflectionDescription: string | undefined;
}
