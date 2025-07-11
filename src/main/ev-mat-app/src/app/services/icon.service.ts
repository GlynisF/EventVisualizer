import {Injectable} from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import {MatIconRegistry} from '@angular/material/icon';


@Injectable({ providedIn: 'root' })
export class AssetService {
  private readonly iconNames = new Set<string>();
  private readonly videoMap = new Map<string, string>();

  constructor(
    private iconRegistry: MatIconRegistry,
    private sanitizer: DomSanitizer
  ) {
    this.registerVideo('intro-video', '/ink_blob.mp4');
  }

  registerIcon(name: string, path?: string): void {
    if (!this.iconNames.has(name)) {
      const safePath = this.sanitizer.bypassSecurityTrustResourceUrl(
        path || `/${name}.svg`
      );
      this.iconRegistry.addSvgIcon(name, safePath);
      this.iconNames.add(name);
    }
  }

  getIconNames(): string[] {
    return Array.from(this.iconNames);
  }

  registerVideo(name: string, url: string): void {
    this.videoMap.set(name, url);
  }

  getVideoUrl(name: string): string | undefined {
    return this.videoMap.get(name);
  }

  getAllVideoNames(): string[] {
    return Array.from(this.videoMap.keys());
  }
}
