export interface Notebook {
  id?: number;
  title?: string;
  events?: Event[];
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
  startTime: Date | string | null;
  endTime: Date | string | null;
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
  eventId?: number | null;
  goalDescription: string | null;

}

export interface Note {
  eventId?: number | null;
  noteDescription: string | null;

}

export interface Reflection {
  eventId?: number | null;
  reflectionDescription: string | null;
}
