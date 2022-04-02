package com.hydroyura.TechDocsManager.Service.Composite;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hydroyura.TechDocsManager.Service.Composite.Elements.ISpecificationRow;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowType;



public interface ICompositeUtilities {

	// Сжимает Лист лонгов до 1 значения
	public static Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> compressMap(Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> map) {
		
		map.forEach(new BiConsumer<SpecificationRowType, Map<ISpecificationRow, List<Long>>>() {
			@Override
			public void accept(SpecificationRowType t, Map<ISpecificationRow, List<Long>> u) {
				u.forEach(new BiConsumer<ISpecificationRow, List<Long>>() {
					@Override
					public void accept(ISpecificationRow t1, List<Long> u1) {
						u.put(t1, Arrays.asList(u1.stream().mapToLong(l -> l).sum()));
					}
				});
			}
		});
		
		return map;
	}

	// умножаем количество в листе
	public static Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> increaseMapCount2(Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> map, long count) {
		Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> updateMap = new HashMap<>();
		map.forEach(new BiConsumer<SpecificationRowType, Map<ISpecificationRow, List<Long>>>() {

			@Override
			public void accept(SpecificationRowType k, Map<ISpecificationRow, List<Long>> v) {
				v.forEach(new BiConsumer<ISpecificationRow, List<Long>>() {
					@Override
					public void accept(ISpecificationRow t, List<Long> u) {
						v.put(t, u.stream().mapToLong(l -> l * count).boxed().collect(Collectors.toList()));
					}
				});
				updateMap.put(k, v);
			}	
		});
		return updateMap;
	}

	
	public static Map<ISpecificationRow, List<Long>> mergeMap(Map<ISpecificationRow, List<Long>> map1, Map<ISpecificationRow, List<Long>> map2) {
		map2.forEach(new BiConsumer<ISpecificationRow, List<Long>>() {
			@Override
			public void accept(ISpecificationRow t, List<Long> u) {
				map1.merge(t, u, new BiFunction<List<Long>, List<Long>, List<Long>>() {

					@Override
					public List<Long> apply(List<Long> t1, List<Long> u1) {
						return Stream.concat(t1.stream(), u1.stream()).collect(Collectors.toList());
					}
					
				});
			}	
		});
		return map1;
	}
	
	
	public static Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> mergeMap1(Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> map1, Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> map2) {
		
		map2.forEach(new BiConsumer<SpecificationRowType, Map<ISpecificationRow, List<Long>>>() {

			@Override
			public void accept(SpecificationRowType t, Map<ISpecificationRow, List<Long>> u) {
				map1.merge(t, u, new BiFunction<Map<ISpecificationRow, List<Long>>, Map<ISpecificationRow, List<Long>>, Map<ISpecificationRow, List<Long>>>() {

					@Override
					public Map<ISpecificationRow, List<Long>> apply(Map<ISpecificationRow, List<Long>> t1, Map<ISpecificationRow, List<Long>> u1) {
						return mergeMap(t1, u1);
					}

				});
			}
		});
		
		return map1;
	}
}

