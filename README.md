# org-mode-sms-inbox #
[](https://github.com/dguilak/org-mode-sms-inbox)

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

Easily capture ideas or TODOs into org-mode through an app almost everyone has installed: SMS!

[Read about this silly project](http://dbg.io/org-mode-sms-inbox)

![Sample conversation with Emacs](/img/sample-conversation.png)

# Prerequisites

  * You'll need your org-mode agenda files synced via Dropbox
  * A [Twilio](http://twilio.com/) account and phone number
  * An [IFTTT](http://ifttt.com/) account
  * An SMS-enabled mobile phone
  
# How it works

  1. You text your own personal org-mode unicorn number
  2. The thing you texted gets appended to a file in Dropbox you specify
  3. You'll see it next time you open up org-mode

# Installation

The code is *super* simple, but you'll have to do a bit of setup on Twilio and IFTTT.

## Twilio

Sign up for a [Twilio account](http://twilio.com/), and snag a new phone number.

## IFTTT

Sign up for an [IFTTT account](http://ifttt.com/) and install [this recipe](https://ifttt.com/recipes/419577-append-to-dropbox-from-maker) - make sure to note the URL it gives you! You'll need it in a sec.

## Deploy

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

If you want to just deploy this to Heroku (the free tier is *more* than enough for an applcation only you are using), click the button
above and enter the two config variables you need:

    IFTTT_DROPBOX_URL,       the IFTTT Maker URL you got when creating the above recipe
    ALLOWED_INCOMING_NUMBER, the phone number that's allowed to add inbox items (+15555555555)

and then take your new instance's incoming URL (e.g., <heroku-instance-name>.herokuapp.com/incoming) and
set that in Twilio as the webhook for when the number receives a text message.

That's it! Shoot a text message to your shiny new inbox and make sure it shows up in your Dropbox folder
that you set when setting up the IFTTT recipe.

You can, of course, deploy this anywhere you'd want. I just used Heroku because I knew it'd be easy
to replicate and it'll run easily on the free tier.

## Known Issues

It takes the Heroku instance a little over 15 seconds to awake once it gets some traffic coming its way. Unfortunately,
Twilio's incoming message API has a 15-second timeout, so you're going to get an `HTTP 11200` error because it'll think the
server is unresponsive, and you won't get the "Got it!" message. Any subsequent messages before it falls asleep again
will provide the right confirmation message.

The important thing is that your input gets logged, which is still the case.


## FAQ

### Why didn't you build note-reading functionality?

Honestly, when I'm out-and-about, I never really need to find or read these TODOs or notes, so
I just built what I needed. Also, see below.

### Wait, why don't you just hook Twilio to IFTTT? 

Great question! This started out as an exercise to get some experience with deploying a Clojure application.

While this app is more than is necessary right now, having an explicit backend also allows for
further functionality to be built out later (like finding notes).

## Contributing

Send me a PR, why not?

## Disclaimer

Use at your own risk, I have by no means tested this extensively.

## License

    org-mode-sms-inbox
    Copyright (C) 2016 Daniel Guilak
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.

