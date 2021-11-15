My implementation of a hybrid image generator using convolution. It takes two images and combines them using filters to produce a merged image.

image A → low pass filtered
<br>
image B → high pass filtered

image A lows + image B highs = hybrid image

A hybrid image will look like image A close up, but as you get further away will more closely resemble image B.
<br>
<br>
Hybrid image:
<br>
<br>
<img src="/images/outputs/hybrid.jpg"/>
<br>
<br>
Image A (left) and image B (right):
<br>
<br>
<img width="40%" src="/images/lollipop cropped.png"/>
<img width="40%" src="/images/stop.png"/>
<br>
<br>
Hybrid image as a cascade (to demonstrate the effect of scaling):
<br>
<br>
<img src="/images/outputs/cascade.jpg"/>