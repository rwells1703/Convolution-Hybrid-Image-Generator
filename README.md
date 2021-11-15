My implementation of a hybrid image generator using convolution. It takes two images and combines them using filters to produce a merged image.

image A → low pass filtered
<br>
image B → high pass filtered

image A low + image B high = hybrid image

A hybrid image will look like image A close up, but as you get further away will more closely resemble image B.
<br>
<br>
Image A (left) and image B (right):
<br>
<br>
<img src="/images/stop.png"/>
<img src="/images/lollipop cropped.png"/>
<br>
<br>
Hybrid image:
<img src="/images/outputs/hybrid.jpg"/>
<br>
<br>
Hybrid image as a cascade (to demonstrate the effect of scaling):
<img height="504" width="378" src="/images/outputs/cascade.jpg"/>