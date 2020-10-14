+ если вы знаете, о том, что метод, включающий в себя string s = string.replaceAll(regex, value) часто вызвается, 
то его необходимо заменить на Pattern.compile(regex).matching(s).replaceAll(value)