SimpleNLG  [![Build Status](https://secure.travis-ci.org/delver/simplenlg.png)](http://travis-ci.org/delver/simplenlg)
=========

Simplenlg is a simple Java API designed to facilitate the generation of Natural
Language. It was originally developed at the
[University of Aberdeen's Department of Computing Science](http://www.csd.abdn.ac.uk/).

This (mavenized) git repo is intended to shadow the latest source version from
https://code.google.com/p/simplenlg/, but augment the code with a maven ```pom.xml``` file with
http://repo.delver.io as the initial repository.

**Note:** No additional functionality (beyond Mavenization & Travis CI) will
ever be added. Caveat: in order to get this project to build on modern JDKs, a 
[Stack Overflow](http://stackoverflow.com/questions/3289644/define-spring-jaxb-namespaces-without-using-namespaceprefixmapper#3307076)
question recommends changing deprecated references of ```com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;``` 
to ```com.sun.xml.bind.marshaller.NamespacePrefixMapper;```; see [Recording.java](https://github.com/delver/simplenlg/blob/master/src/simplenlg/xmlrealiser/Recording.java#L45)
for details of the file that changed.

License
-------
See https://github.com/delver/simplenlg/blob/master/docs/LICENSE.txt
