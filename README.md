assets-tag
==========

Assets-tag is a tag library for versioning, minifing and aggregating asset files (javascript and stylesheet).

Versioning
----------

Because fucking IE9 caches asset files more than necessary, when we updated asset files, IE9 .
So we have to append the version number to each asset files, and when we update asset files we have to change the version number in file name.

  foo-1.0.js  -> foo-1.1.js

Each and every time, we rewrite all JSP files including the asset file. How terrible it is!
Using assets-tag, this work will be simple.

  <assets:javascript src="foo.js">  ->  <script type="text/javascript" src="foo-1.0.js"></script>

Minifing
--------

Assets-tag can minify the asset file when the option "minify" is true.
