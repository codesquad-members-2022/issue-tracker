package team29.hoorry.issuetracker.core.common.search;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchParamParser {

	public static MultiValueMap<SearchFilter, String> parse(String query) {
		MultiValueMap<SearchFilter, String> filters = new LinkedMultiValueMap<>();

		if (query == null) {
			return filters;
		}

		String[] params = parseParams(query);

		for (String param : params) {
			convertParamToFilter(filters, param);
		}

		return filters;
	}

	private static void convertParamToFilter(MultiValueMap<SearchFilter, String> filters, String param) {
		String[] filter = param.split(":");

		if (isSearchParam(filter)) {
			filters.addIfAbsent(SearchFilter.SEARCH_PARAM, filter[0]);
			return;
		}

		String filterAttribute = filter[0];
		String filterCondition = filter[1];
		SearchFilter searchFilter = SearchFilter.of(filterAttribute);

		if (searchFilter == SearchFilter.NONE) {
			return;
		}
		filters.add(searchFilter, filterCondition);
	}

	private static String[] parseParams(String query) {
		String[] params = query.split("\\+");
		for (int i = 0; i < params.length; i++) {
			params[i] = decode(params[i]);
		}
		return params;
	}

	private static String decode(String param) {
		return URLDecoder.decode(param, StandardCharsets.UTF_8);
	}

	private static boolean isSearchParam(String[] filter) {
		return filter.length == 1;
	}
}
