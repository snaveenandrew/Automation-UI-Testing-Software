/**********************************************************************
 * File:        topitch.h  (Formerly to_pitch.h)
 * Description: Code to determine fixed pitchness and the pitch if fixed.
 * Author:		Ray Smith
 * Created:		Tue Aug 24 16:57:29 BST 1993
 *
 * (C) Copyright 1993, Hewlett-Packard Ltd.
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

#ifndef           TOPITCH_H
#define           TOPITCH_H

#include          "blobbox.h"
#include          "notdll.h"

extern BOOL_VAR_H (textord_debug_pitch_test, FALSE,
"Debug on fixed pitch test");
extern BOOL_VAR_H (textord_debug_pitch_metric, FALSE,
"Write full metric stuff");
extern BOOL_VAR_H (textord_show_row_cuts, FALSE, "Draw row-level cuts");
extern BOOL_VAR_H (textord_show_page_cuts, FALSE, "Draw page-level cuts");
extern BOOL_VAR_H (textord_pitch_cheat, FALSE,
"Use correct answer for fixed/prop");
extern BOOL_VAR_H (textord_blockndoc_fixed, TRUE,
"Attempt whole doc/block fixed pitch");
extern BOOL_VAR_H (textord_fast_pitch_test, FALSE,
"Do even faster pitch algorithm");
extern double_VAR_H (textord_projection_scale, 0.125,
"Ding rate for mid-cuts");
extern double_VAR_H (textord_balance_factor, 2.0,
"Ding rate for unbalanced char cells");
extern double_VAR_H (textord_repch_width_variance, 0.2,
"Max width change of gap/blob");
void compute_fixed_pitch(                             //determine pitch
                         ICOORD page_tr,              //top right
                         TO_BLOCK_LIST *port_blocks,  //input list
                         float gradient,              //page skew
                         FCOORD rotation,             //for drawing
                         BOOL8 testing_on             //correct orientation
                        );
void fix_row_pitch(                        //get some value
                   TO_ROW *bad_row,        //row to fix
                   TO_BLOCK *bad_block,    //block of bad_row
                   TO_BLOCK_LIST *blocks,  //blocks to scan
                   INT32 row_target,       //number of row
                   INT32 block_target      //number of block
                  );
void compute_block_pitch(                    //process each block
                         TO_BLOCK *block,    //input list
                         FCOORD rotation,    //for drawing
                         INT32 block_index,  //block number
                         BOOL8 testing_on    //correct orientation
                        );
BOOL8 compute_rows_pitch(                    //find line stats
                         TO_BLOCK *block,    //block to do
                         INT32 block_index,  //block number
                         BOOL8 testing_on    //correct orientation
                        );
BOOL8 try_doc_fixed(                             //determine pitch
                    ICOORD page_tr,              //top right
                    TO_BLOCK_LIST *port_blocks,  //input list
                    float gradient               //page skew
                   );
BOOL8 try_block_fixed(                   //find line stats
                      TO_BLOCK *block,   //block to do
                      INT32 block_index  //block number
                     );
BOOL8 try_rows_fixed(                    //find line stats
                     TO_BLOCK *block,    //block to do
                     INT32 block_index,  //block number
                     BOOL8 testing_on    //correct orientation
                    );
void print_block_counts(                   //find line stats
                        TO_BLOCK *block,   //block to do
                        INT32 block_index  //block number
                       );
void count_block_votes(                   //find line stats
                       TO_BLOCK *block,   //block to do
                       INT32 &def_fixed,  //add to counts
                       INT32 &def_prop,
                       INT32 &maybe_fixed,
                       INT32 &maybe_prop,
                       INT32 &corr_fixed,
                       INT32 &corr_prop,
                       INT32 &dunno);
BOOL8 row_pitch_stats(                  //find line stats
                      TO_ROW *row,      //current row
                      INT32 maxwidth,   //of spaces
                      BOOL8 testing_on  //correct orientation
                     );
BOOL8 find_row_pitch(                    //find lines
                     TO_ROW *row,        //row to do
                     INT32 maxwidth,     //max permitted space
                     INT32 dm_gap,       //ignorable gaps
                     TO_BLOCK *block,    //block of row
                     INT32 block_index,  //block_number
                     INT32 row_index,    //number of row
                     BOOL8 testing_on    //correct orientation
                    );
BOOL8 fixed_pitch_row(                   //find lines
                      TO_ROW *row,       //row to do
                      INT32 block_index  //block_number
                     );
BOOL8 count_pitch_stats(                       //find lines
                        TO_ROW *row,           //row to do
                        STATS *gap_stats,      //blob gaps
                        STATS *pitch_stats,    //centre-centre stats
                        float initial_pitch,   //guess at pitch
                        float min_space,       //estimate space size
                        BOOL8 ignore_outsize,  //discard big objects
                        BOOL8 split_outsize,   //split big objects
                        INT32 dm_gap           //ignorable gaps
                       );
float tune_row_pitch(                             //find fp cells
                     TO_ROW *row,                 //row to do
                     STATS *projection,           //vertical projection
                     INT16 projection_left,       //edge of projection
                     INT16 projection_right,      //edge of projection
                     float space_size,            //size of blank
                     float &initial_pitch,        //guess at pitch
                     float &best_sp_sd,           //space sd
                     INT16 &best_mid_cuts,        //no of cheap cuts
                     ICOORDELT_LIST *best_cells,  //row cells
                     BOOL8 testing_on             //inidividual words
                    );
float tune_row_pitch2(                             //find fp cells
                      TO_ROW *row,                 //row to do
                      STATS *projection,           //vertical projection
                      INT16 projection_left,       //edge of projection
                      INT16 projection_right,      //edge of projection
                      float space_size,            //size of blank
                      float &initial_pitch,        //guess at pitch
                      float &best_sp_sd,           //space sd
                      INT16 &best_mid_cuts,        //no of cheap cuts
                      ICOORDELT_LIST *best_cells,  //row cells
                      BOOL8 testing_on             //inidividual words
                     );
float compute_pitch_sd (         //find fp cells
TO_ROW * row,                    //row to do
STATS * projection,              //vertical projection
INT16 projection_left,           //edge
INT16 projection_right,          //edge
float space_size,                //size of blank
float initial_pitch,             //guess at pitch
float &sp_sd,                    //space sd
INT16 & mid_cuts,                //no of free cuts
ICOORDELT_LIST * row_cells,      //list of chop pts
BOOL8 testing_on,                //inidividual words
INT16 start = 0,                 //start of good range
INT16 end = 0                    //end of good range
);
float compute_pitch_sd2 (        //find fp cells
TO_ROW * row,                    //row to do
STATS * projection,              //vertical projection
INT16 projection_left,           //edge
INT16 projection_right,          //edge
float initial_pitch,             //guess at pitch
INT16 & occupation,              //no of occupied cells
INT16 & mid_cuts,                //no of free cuts
ICOORDELT_LIST * row_cells,      //list of chop pts
BOOL8 testing_on,                //inidividual words
INT16 start = 0,                 //start of good range
INT16 end = 0                    //end of good range
);
void print_pitch_sd(                        //find fp cells
                    TO_ROW *row,            //row to do
                    STATS *projection,      //vertical projection
                    INT16 projection_left,  //edges //size of blank
                    INT16 projection_right,
                    float space_size,
                    float initial_pitch     //guess at pitch
                   );
int sort_floats2(                   //qsort function
                 const void *arg1,  //ptrs to floats
                 const void *arg2);
void find_repeated_chars(                  //search for equal chars
                         TO_BLOCK *block,  //block to search
                         BOOL8 testing_on  //dbug mode
                        );
void plot_fp_word(                  //draw block of words
                  TO_BLOCK *block,  //block to draw
                  float pitch,      //pitch to draw with
                  float nonspace    //for space threshold
                 );
#endif
