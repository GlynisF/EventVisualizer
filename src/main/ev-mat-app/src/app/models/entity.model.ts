export interface Notebook {
  id?: number;
  title?: string;
  events?: Event[];
  eventId?: number | null;
}

export interface Event {
  id?: number;
  eventName: string;
  notebookId?: number | null;
  details?: Detail[] | [];
  goal?: Goal | null;
  note?: Note | null
  reflection?: Reflection | null;
}

export interface Detail {
  id?: number;
  dateOfEvent: Date | null;
  startTime: Date | null;
  endTime: Date | null;
  description: string;
  performers?: Performer[] | [];
  locations?: Location[] | [];
  eventId?: number | null;
}

export interface Performer {
  id?: number;
  fullName: string | null;
  moniker: string | null;
  email: string | null;
  performanceFee: number | null;
  detailId?: number | null;
}

export interface Location {
  id?: number;
  locationName: string | null;
  phoneNumber: string | null;
  address: string | null;
  address2?: string;
  city: string | null;
  state: string | null;
  zip: string | null;
  website: string | null;
  accessible: boolean | false;
  detailId?: number | null;
  fullAddress?: string | null;
}

export interface Goal {
  id?: number;
  goalDescription: string | null;
  eventId?: number | null;
}

export interface Note {
  id?: number;
  noteDescription: string | null;
  eventId?: number | null;
}

export interface Reflection {
  id?: number;
  reflectionDescription: string | null;
  eventId?: number | null;
}
