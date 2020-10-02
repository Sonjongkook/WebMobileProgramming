import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {element} from 'protractor';

@Component({
  selector: 'app-search-recipe',
  templateUrl: './search-recipe.component.html',
  styleUrls: ['./search-recipe.component.css']
})
export class SearchRecipeComponent implements OnInit {
  @ViewChild('recipe') recipes: ElementRef;
  @ViewChild('place') places: ElementRef;
  recipeValue: any;
  placeValue: any;
  venueList = [];
  recipeList = [];

  currentLat: any;
  currentLong: any;
  geolocationPosition: any;



  constructor(private _http: HttpClient) {
  }

  ngOnInit() {

    window.navigator.geolocation.getCurrentPosition(
      position => {
        this.geolocationPosition = position;
        this.currentLat = position.coords.latitude;
        this.currentLong = position.coords.longitude;
      });
  }

  getVenues() {

    this.recipeValue = this.recipes.nativeElement.value;
    this.placeValue = this.places.nativeElement.value;


    if (this.recipeValue !== null) {
      /**
       * Write code to get recipe
       */
      this._http.get("https://api.edamam.com/search?q=" + this.recipeValue + `&app_id=852904e7&app_key=469e8f4d67d21e09e2d3cb75bf188b30`)
        .subscribe((res: any) => {
        // Assign recipe list and key value as res.hits
        this.recipeList = Object.keys(res.hits).map(function(elem) {
          const target = res.hits[elem].recipe;
          console.log(target);
          return {name: target.label, icon: target.image, url: target.url};
        });
      });



      }


      if (this.placeValue != null && this.placeValue !== '' && this.recipeValue != null && this.recipeValue !== '') {
        /**
         * Write code to get place
         */
        this._http.get('https://api.foursquare.com/v2/venues/search?client_id=CV15KH2QP5PRRWT0C5D10ZTTUVKPDOLZSISOUIM2YV5SISZ0' +
          '&client_secret=V3QX1CTSJUQ4YVQXUNQ10Y04JUZ0GKMGQUKJIZ55LO2JVUGT&v=20201020' + '&near=' + this.placeValue + '&query=' +this.recipeValue)
          .subscribe(res => {
            console.log(res);
            this.venueList = res['response']['venues'];
          }, error => {});
      }
      }
  }

