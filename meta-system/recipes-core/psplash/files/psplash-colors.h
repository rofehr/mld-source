/*
 *  pslash - a lightweight framebuffer splashscreen for embedded devices.
 *
 *  Copyright (c) 2012 sleep(5) ltd
 *  Author: Tomas Frydrych <tomas@sleepfive.com>
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2, or (at your option)
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 */

#ifndef _HAVE_PSPLASH_COLORS_H
#define _HAVE_PSPLASH_COLORS_H

/*
#define     NONE                        RGB(0x00, 0x04, 0x00)
#define     BLACK                       RGB(0x00, 0x00, 0x00)
#define     DKGRAY                      RGB(0x40, 0x40, 0x40)
#define     GRAY                        RGB(0x80, 0x80, 0x80)
#define     LTGRAY                      RGB(0xA0, 0xA0, 0xA0)
#define     WHITE                       RGB(0xFF, 0xFF, 0xFF)
#define     DKRED                       RGB(0x60, 0x00, 0x00)
#define     RED                         RGB(0xFF, 0x00, 0x00)
#define     LTRED                       RGB(0xFF, 0x40, 0x40)
#define     DKGREEN                     RGB(0x00, 0x70, 0x00)
#define     GREEN                       RGB(0x00, 0x8F, 0x00)
#define     LTGREEN                     RGB(0x50, 0xFF, 0x50)
#define     DKBLUE                      RGB(0x00, 0x00, 0x60)
#define     BLUE                        RGB(0x10, 0x10, 0xFF)
#define     LTBLUE                      RGB(0x60, 0x60, 0xFF)
#define     DKYELLOW                    RGB(0x80, 0x80, 0x00)
#define     YELLOW                      RGB(0xFF, 0xFF, 0x00)
#define     LTYELLOW                    RGB(0xFF, 0xFF, 0x80)
#define     DKMAGENTA                   RGB(0x60, 0x00, 0x60)
#define     MAGENTA                     RGB(0xFF, 0x00, 0xFF)
#define     LTMAGENTA                   RGB(0xFF, 0x60, 0xFF)
#define     DKCYAN                      RGB(0x00, 0x60, 0x60)
#define     CYAN                        RGB(0x00, 0xB0, 0xB0)
#define     LTCYAN                      RGB(0x60, 0xFF, 0xFF)
#define     GOLD                        RGB(0x90, 0x90, 0x30)
*/

/* This is the overall background color */
#define PSPLASH_BACKGROUND_COLOR  0x00,0x00,0x00

/* This is the color of any text output */
#define PSPLASH_TEXT_COLOR 0xA0, 0xA0, 0xA0

/* This is the color of the progress bar indicator */
#define PSPLASH_BAR_COLOR 0x80, 0x80, 0x80

/* This is the color of the progress bar background */
#define PSPLASH_BAR_BACKGROUND_COLOR 0xFF,0xFF,0xFF

#endif
