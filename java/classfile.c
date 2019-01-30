

type AttributeTable struct {
	attributes []AttributeInfo
}

type ConstantPool struct {
	cf      *ClassFile
	cpInfos []ConstantInfo
}

type ClassFile struct {
	magic           uint32
	minorVersion uint16
	majorVersion uint16
	constantPool *ConstantPool
	accessFlags  uint16
	thisClass    uint16
	superClass   uint16
	interfaces   []uint16
	fields       []*MemberInfo
	methods      []*MemberInfo
	AttributeTable
}

