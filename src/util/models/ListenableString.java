package util.models;
@util.annotations.StructurePattern(util.annotations.StructurePatternNames.VECTOR_PATTERN)

public interface ListenableString extends ListenableVector<Character> {
		public void setString(String newVal);


}
