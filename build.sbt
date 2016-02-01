resolvers += Resolver.file("buggy", file("repo"))(
	Patterns(
		ivyPatterns =  Seq("[organization]/[module]/[revision]/ivy.xml"), 
		artifactPatterns = Seq("[organization]/[module]/[revision]/[artifact].[ext]"),
		isMavenCompatible = false,
		descriptorOptional = true, 
		skipConsistencyCheck = true
	)	
)

//following lines cannot be resolved
libraryDependencies += "a" % "b" % "1.0.0" % "compile->runtime" artifacts(Artifact("b1", "jar", "jar"))

libraryDependencies += "a" % "b" % "1.0.0" % "test->runtime" artifacts(Artifact("b1", "jar", "jar"))

//however changing this to version below fixes problem
libraryDependencies += "a" % "b" % "1.0.0" % "compile,test->runtime" artifacts(Artifact("b1", "jar", "jar"))
