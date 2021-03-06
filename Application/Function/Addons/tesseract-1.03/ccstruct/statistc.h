/**********************************************************************
 * File:        statistc.h  (Formerly stats.h)
 * Description: Class description for STATS class.
 * Author:					Ray Smith
 * Created:					Mon Feb 04 16:19:07 GMT 1991
 *
 * (C) Copyright 1991, Hewlett-Packard Ltd.
 ** Licensed under the Apache License, Version 2.0 (the "License");
 ** you may not use this file except in compliance with the License.
 ** You may obtain a copy of the License at
 ** http://www.apache.org/licenses/LICENSE-2.0
 ** Unless required by applicable law or agreed to in writing, software
 ** distributed under the License is distributed on an "AS IS" BASIS,
 ** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ** See the License for the specific language governing permissions and
 ** limitations under the License.
 *
 **********************************************************************/

#ifndef           STATISTC_H
#define           STATISTC_H

#include          <stdio.h>
#include          "grphics.h"

class DLLSYM STATS               //statistics package
{
  INT32 rangemin;                //min of range
  INT32 rangemax;                //max of range
  INT32 total_count;             //no of samples
  INT32 *buckets;                //array of cells

  public:
    STATS(             //constructor
          INT32 min,   //min of range
          INT32 max);  //max of range
    STATS();  //empty for arrays

    ~STATS ();                   //destructor

    bool set_range(             //change range
                   INT32 min,   //min of range
                   INT32 max);  //max of range

    void clear();  //empty buckets

    void add(               //add sample
             INT32 value,   //bucket
             INT32 count);  //no to add

    INT32 mode();  //get mode of samples

    float mean();  //get mean of samples

    float sd();  //standard deviation

    float ile(              //percentile
              float frac);  //[0,1] for percentil

    INT32 min_bucket();  //Find min

    INT32 max_bucket();  //Find max

    float median();  //get median of samples

    void smooth(                //apply blurring
                INT32 factor);  //filter to stats
    INT32 cluster(                     //cluster samples
                  float lower,         //thresholds
                  float upper,
                  float multiple,      //distance threshold
                  INT32 max_clusters,  //max no to make
                  STATS *clusters);    //array of clusters

    INT32 pile_count(             //access function
                     INT32 value  //pile to count
                    ) {
      return value > rangemin ? (value < rangemax
        ? buckets[value -
        rangemin] : buckets[rangemax -
        rangemin -
        1]) : buckets[0];
    }

    INT32 get_total() {  //access function
      return total_count;        //total of all piles
    }

    BOOL8 local_min(  //test local minness
                    INT32 x);

    void print(              //print summary/table
               FILE *fp,     //file to print on
               BOOL8 dump);  //dump whole table

    void short_print(              //print summary/table
                     FILE *fp,     //file to print on
                     BOOL8 dump);  //dump whole table

    void plot(                 //draw histogram rect
              WINDOW window,   //window to draw in
              float xorigin,   //origin of histo
              float yorigin,   //gram
              float xscale,    //size of one unit
              float yscale,    //size of one uint
              COLOUR colour);  //colour to draw in

    void plotline(                 //draw histogram line
                  WINDOW window,   //window to draw in
                  float xorigin,   //origin of histo
                  float yorigin,   //gram
                  float xscale,    //size of one unit
                  float yscale,    //size of one uint
                  COLOUR colour);  //colour to draw in
};
DLLSYM INT32 choose_nth_item(               //fast median
                             INT32 index,   //index to choose
                             float *array,  //array of items
                             INT32 count    //no of items
                            );
DLLSYM INT32 choose_nth_item (   //fast median
INT32 index,                     //index to choose
void *array,                     //array of items
INT32 count,                     //no of items
size_t size,                     //element size
                                 //comparator
int (*compar) (const void *, const void *)
);
void swap_entries(               //swap in place
                  void *array,   //array of entries
                  size_t size,   //size of entry
                  INT32 index1,  //entries to swap
                  INT32 index2);
#endif
